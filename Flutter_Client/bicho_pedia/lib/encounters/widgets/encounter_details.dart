import 'package:bicho_pedia/encounters/model/encounter_detail_response.dart';
import 'package:bicho_pedia/encounters/widgets/encounter_detail_location.dart';
import 'package:bicho_pedia/encounters/widgets/encounters_details_gallery.dart';
import 'package:flutter/material.dart';
import 'package:get/get_connect/http/src/utils/utils.dart';

class EncounterDetailsWidget extends StatefulWidget {
  final EncounterDetailsResponse encounter;

  const EncounterDetailsWidget({super.key, required this.encounter});

  @override
  State<EncounterDetailsWidget> createState() => _EncounterDetailsWidgetState();
}

class _EncounterDetailsWidgetState extends State<EncounterDetailsWidget> {
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
        length: 3,
        child: Scaffold(
          appBar: AppBar(
              backgroundColor: const Color.fromARGB(255, 19, 20, 13),
              automaticallyImplyLeading: F,
              toolbarHeight: 450,
              title: Container(
                height: 450,
                decoration: BoxDecoration(
                  borderRadius: const BorderRadius.all(Radius.circular(24)),
                  image: DecorationImage(
                    alignment: Alignment.topCenter,
                    fit: BoxFit.cover,
                    image: NetworkImage(
                        'http://10.0.2.2:8080/download/${widget.encounter.mainPhoto!}'),
                  ),
                ),
                child: Container(
                    alignment: Alignment.bottomCenter,
                    decoration: const BoxDecoration(
                      borderRadius: BorderRadius.all(Radius.circular(24)),
                      gradient: LinearGradient(
                        begin: Alignment.topCenter,
                        end: Alignment.bottomCenter,
                        stops: [0.1, 0.55],
                        colors: [
                          Color.fromARGB(0, 0, 0, 0),
                          Color.fromARGB(233, 19, 20, 13),
                        ],
                      ),
                    ),
                    child: Column(
                        mainAxisAlignment: MainAxisAlignment.end,
                        children: [
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 20),
                            height: 40,
                            child: Row(
                              children: [
                                Text(
                                  widget.encounter.scientificName!,
                                  style: const TextStyle(
                                      overflow: TextOverflow.ellipsis,
                                      fontSize: 20,
                                      color: Colors.white,
                                      fontFamily: 'OpenSans',
                                      fontWeight: FontWeight.w700),
                                ),
                                Padding(
                                  padding: const EdgeInsets.all(5.0),
                                  child: Image(
                                      // ignore: prefer_interpolation_to_compose_strings
                                      image: AssetImage('assets/' +
                                          widget.encounter.danger! +
                                          ".png")),
                                )
                              ],
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(bottom: 8.0),
                            child: Container(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 20),
                              alignment: Alignment.centerLeft,
                              height: 20,
                              child: Row(
                                children: [
                                  const Text(
                                    'By ',
                                    style: TextStyle(
                                        fontSize: 10,
                                        color: Colors.white,
                                        fontFamily: 'OpenSans',
                                        fontWeight: FontWeight.w700),
                                  ),
                                  Text(
                                    widget.encounter.username!,
                                    style: const TextStyle(
                                        fontSize: 10,
                                        color: Colors.amber,
                                        fontFamily: 'OpenSans',
                                        fontWeight: FontWeight.w700),
                                  ),
                                ],
                              ),
                            ),
                          ),
                          const TabBar(dividerHeight: 0, tabs: [
                            Tab(
                              child: Text(
                                'Description',
                                style: TextStyle(
                                    fontFamily: 'OpenSans',
                                    color: Colors.white54),
                              ),
                            ),
                            Tab(
                              child: Text(
                                'Gallery',
                                style: TextStyle(
                                    fontFamily: 'OpenSans',
                                    color: Colors.white54),
                              ),
                            ),
                            Tab(
                              child: Text(
                                'Location',
                                style: TextStyle(
                                    fontFamily: 'OpenSans',
                                    color: Colors.white54),
                              ),
                            ),
                          ])
                        ])),
              )),
          body: Container(
            color: const Color.fromARGB(255, 19, 20, 13),
            child: TabBarView(children: [
              Container(
                decoration: const BoxDecoration(
                  color: Color.fromARGB(255, 19, 20, 13),
                ),
                height: 435, // Altura del NavigationBar
                width: MediaQuery.of(context).size.width,
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: Padding(
                  padding: const EdgeInsets.only(top: 20.0),
                  child: Text(
                    widget.encounter.description!,
                    style: const TextStyle(color: Colors.white),
                  ),
                ),
              ),
              Container(
                decoration: const BoxDecoration(
                  color: Color.fromARGB(255, 19, 20, 13),
                ),
                height: 435, // Altura del NavigationBar
                width: MediaQuery.of(context).size.width,
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SafeArea(
                    child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: <Widget>[
                    Expanded(
                        child: EncounterDetailsGallery(
                            media: widget.encounter.media!))
                  ],
                )),
              ),
              Container(
                decoration: const BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  color: Color.fromARGB(255, 19, 20, 13),
                ),
                height: 435, // Altura del NavigationBar
                width: MediaQuery.of(context).size.width,
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SafeArea(
                    child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: <Widget>[
                    Expanded(
                        child: EncounterDetailsLocation(
                      lat: widget.encounter.lat!,
                      long: widget.encounter.lon!,
                    ))
                  ],
                )),
              )
            ]),
          ),
        ));
  }
}
