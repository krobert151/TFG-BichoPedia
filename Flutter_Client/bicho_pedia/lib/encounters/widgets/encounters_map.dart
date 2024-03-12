import 'dart:async';

import 'package:bicho_pedia/encounters/bloc/markes/markers_bloc.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository_impl.dart';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class MapEncounters extends StatefulWidget {
  const MapEncounters({super.key});

  @override
  State<MapEncounters> createState() => _MapEncountersState();
}

class _MapEncountersState extends State<MapEncounters> {
  final Completer<GoogleMapController> _completer =
      Completer<GoogleMapController>();
  late EncountersRepository encountersRepository;
  late Set<Marker> markers = {};

  @override
  void initState() {
    encountersRepository = EncountersRepositoryImpl();
    _getMarkers();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: FutureBuilder(
        future: _determinePosition(),
        builder: (BuildContext context, AsyncSnapshot<Position> snapshot) {
          if (snapshot.hasData) {
            return GoogleMap(
              mapType: MapType.terrain,
              initialCameraPosition: CameraPosition(
                  target:
                      LatLng(snapshot.data!.latitude, snapshot.data!.longitude),
                  zoom: 14),
              onMapCreated: (GoogleMapController controller) {
                _completer.complete(controller);
              },
              markers: markers, // Utilizar el conjunto de marcadores actual
            );
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          }
          return const Center(child: CircularProgressIndicator());
        },
      ),
    );
  }

  Future<Position> _determinePosition() async {
    bool serviceEnabled;
    LocationPermission permission;

    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      return Future.error('Location services are disabled.');
    }

    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        return Future.error('Location permissions are denied');
      }
    }

    if (permission == LocationPermission.deniedForever) {
      return Future.error(
          'Location permissions are permanently denied, we cannot request permissions.');
    }
    return await Geolocator.getCurrentPosition();
  }

  void _getMarkers() {
    final bloc = MarkersBloc(encountersRepository);
    bloc.add(DoMarkersEvent());

    bloc.stream.listen((MarkersState state) {
      if (state is MarkersSuccess) {
        final newMarkers = state.list.map((mark) {
          return Marker(
            markerId: MarkerId(mark.id!),
            position: LatLng(
                double.parse(mark.latitud!), double.parse(mark.longuitud!)),
            onTap: () {},
          );
        }).toSet();

        setState(() {
          markers = newMarkers;
        });
      }
    });
  }
}
