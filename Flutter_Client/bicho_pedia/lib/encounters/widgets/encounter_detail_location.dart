import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class EncounterDetailsLocation extends StatefulWidget {
  final String lat;
  final String long;

  const EncounterDetailsLocation(
      {super.key, required this.lat, required this.long});

  @override
  State<EncounterDetailsLocation> createState() =>
      _EncounterDetailsLocationState();
}

class _EncounterDetailsLocationState extends State<EncounterDetailsLocation> {
  late Set<Marker> set = {};

  @override
  initState() {
    super.initState();
    set.add(Marker(
        markerId: const MarkerId('Sadam Hudsein'),
        position: LatLng(double.parse(widget.lat), double.parse(widget.long))));
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: const Color.fromARGB(255, 19, 20, 13),
      child: Container(
        decoration: const BoxDecoration(
            borderRadius: BorderRadius.all(Radius.circular(20))),
        child: GoogleMap(
          mapType: MapType.terrain,
          initialCameraPosition: CameraPosition(
              target:
                  LatLng(double.parse(widget.lat), double.parse(widget.long)),
              zoom: 12),
          markers: set,
        ),
      ),
    );
  }
}
