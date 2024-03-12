import 'package:bicho_pedia/species/model/specie_details_response.dart';
import 'package:bicho_pedia/species/widgets/articles_lists.dart';
import 'package:flutter/material.dart';
import 'package:get/get_connect/http/src/utils/utils.dart';

class SpecieDetailsWidget extends StatefulWidget {
  final SpecieDetailsResponse specie;

  const SpecieDetailsWidget({super.key, required this.specie});

  @override
  State<SpecieDetailsWidget> createState() => _SpecieDetailsWidgetState();
}

class _SpecieDetailsWidgetState extends State<SpecieDetailsWidget> {
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
                    image: NetworkImage(widget.specie.mainPhoto!),
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
                                  widget.specie.scientificName!,
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
                                          widget.specie.danger! +
                                          ".png")),
                                )
                              ],
                            ),
                          ),
                          const TabBar(
                              dividerHeight: 0,
                              indicatorColor: Color.fromARGB(255, 190, 222, 97),
                              tabs: [
                                Tab(
                                  child: Text(
                                    'Info',
                                    style: TextStyle(
                                        fontFamily: 'OpenSans',
                                        color: Colors.white54),
                                  ),
                                ),
                                Tab(
                                  child: Text(
                                    'Identification',
                                    style: TextStyle(
                                        fontFamily: 'OpenSans',
                                        color: Colors.white54),
                                  ),
                                ),
                                Tab(
                                  child: Text(
                                    'Cares',
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
                child: ArticlesLists(list: widget.specie.info!),
              ),
              Container(
                decoration: const BoxDecoration(
                  color: Color.fromARGB(255, 19, 20, 13),
                ),
                height: 435, // Altura del NavigationBar
                width: MediaQuery.of(context).size.width,
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: ArticlesLists(list: widget.specie.identification!),
              ),
              Container(
                decoration: const BoxDecoration(
                  color: Color.fromARGB(255, 19, 20, 13),
                ),
                height: 435, // Altura del NavigationBar
                width: MediaQuery.of(context).size.width,
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: ArticlesLists(list: widget.specie.cares!),
              ),
            ]),
          ),
        ));
  }
}
