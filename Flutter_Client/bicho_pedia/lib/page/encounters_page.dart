import 'package:bicho_pedia/encounters/bloc/encounter/encounter_bloc.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository_impl.dart';
import 'package:bicho_pedia/encounters/widgets/encounter_item.dart';
import 'package:bicho_pedia/encounters/widgets/encounters_map.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class EncountersPage extends StatefulWidget {
  const EncountersPage({super.key});

  @override
  State<EncountersPage> createState() => _EncountersPageState();
}

class _EncountersPageState extends State<EncountersPage> {
  late EncountersRepository encountersRepository;

  @override
  void initState() {
    encountersRepository = EncountersRepositoryImpl();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) =>
          EncounterBloc(encountersRepository)..add(DoEncounterEvent(10, 0, '')),
      child: Scaffold(
        backgroundColor: const Color.fromARGB(255, 19, 20, 13),
        appBar: AppBar(
          automaticallyImplyLeading: false,
          toolbarHeight: 80,
          backgroundColor: const Color.fromARGB(255, 19, 20, 13),
          title: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Expanded(
                child: TextFormField(
                  style: const TextStyle(color: Colors.white),
                  decoration: const InputDecoration(
                    hintText: 'Search Encounters',
                    hintStyle:
                        TextStyle(color: Color.fromARGB(255, 255, 255, 255)),
                    border: InputBorder.none,
                  ),
                ),
              ),
              IconButton(
                onPressed: () {},
                icon: const Icon(
                  Icons.search,
                  color: Colors.white,
                ),
              )
            ],
          ),
        ),
        body: BlocBuilder<EncounterBloc, EncounterState>(
          builder: (BuildContext context, EncounterState state) {
            if (state is EncounterInitial) {
              return const Text(
                'Cargando',
                style: TextStyle(
                  fontFamily: 'OpenSans',
                  color: Color.fromARGB(255, 255, 255, 255),
                ),
              );
            } else if (state is EncounterSuccess) {
              return Padding(
                padding: const EdgeInsets.symmetric(horizontal: 12),
                child: SingleChildScrollView(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      TextButton(
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => const MapEncounters()),
                          );
                        },
                        child: Container(
                          height: 150,
                          width: 370,
                          decoration: const BoxDecoration(
                              borderRadius:
                                  BorderRadius.all(Radius.circular(24)),
                              image: DecorationImage(
                                  fit: BoxFit.cover,
                                  image: AssetImage('assets/Map.png'))),
                          child: Container(
                            alignment: Alignment.bottomLeft,
                            decoration: const BoxDecoration(
                                borderRadius:
                                    BorderRadius.all(Radius.circular(10)),
                                gradient: LinearGradient(
                                    begin: Alignment.topCenter,
                                    end: Alignment.bottomCenter,
                                    stops: [
                                      0.1,
                                      0.9
                                    ],
                                    colors: [
                                      Color.fromARGB(0, 0, 0, 0),
                                      Color.fromARGB(233, 19, 20, 13)
                                    ])),
                            child: Container(
                              margin: const EdgeInsets.all(15),
                              padding: const EdgeInsets.all(8.0),
                              child: const Text('Map',
                                  style: TextStyle(
                                    fontFamily: 'OpenSans',
                                    fontWeight: FontWeight.w700,
                                    color: Color.fromARGB(255, 255, 255, 255),
                                  )),
                            ),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.symmetric(vertical: 15),
                        child: Container(
                          height: 40,
                          width: 370,
                          decoration: const BoxDecoration(
                            borderRadius: BorderRadius.all(Radius.circular(8)),
                            color: Color.fromARGB(255, 48, 49, 45),
                          ),
                          child: ListView(
                              scrollDirection: Axis.horizontal,
                              children: [
                                TextButton(
                                    onPressed: () {},
                                    child: Container(
                                      height: 30,
                                      width: 25,
                                      decoration: const BoxDecoration(
                                          image: DecorationImage(
                                              fit: BoxFit.fill,
                                              image: AssetImage(
                                                  'assets/Amphibian.png'))),
                                    )),
                                TextButton(
                                    onPressed: () {},
                                    child: Container(
                                      height: 30,
                                      width: 25,
                                      decoration: const BoxDecoration(
                                          image: DecorationImage(
                                              fit: BoxFit.fill,
                                              image: AssetImage(
                                                  'assets/Arachnid.png'))),
                                    )),
                                TextButton(
                                    onPressed: () {},
                                    child: Container(
                                      height: 30,
                                      width: 25,
                                      decoration: const BoxDecoration(
                                          image: DecorationImage(
                                              fit: BoxFit.fill,
                                              image: AssetImage(
                                                  'assets/Bird.png'))),
                                    )),
                                TextButton(
                                    onPressed: () {},
                                    child: Container(
                                      height: 30,
                                      width: 25,
                                      decoration: const BoxDecoration(
                                          image: DecorationImage(
                                              fit: BoxFit.fill,
                                              image: AssetImage(
                                                  'assets/Fish.png'))),
                                    )),
                                TextButton(
                                    onPressed: () {},
                                    child: Container(
                                      height: 30,
                                      width: 25,
                                      decoration: const BoxDecoration(
                                          image: DecorationImage(
                                              fit: BoxFit.fill,
                                              image: AssetImage(
                                                  'assets/Insect.png'))),
                                    )),
                                TextButton(
                                    onPressed: () {},
                                    child: Container(
                                      height: 30,
                                      width: 25,
                                      decoration: const BoxDecoration(
                                          image: DecorationImage(
                                              fit: BoxFit.fill,
                                              image: AssetImage(
                                                  'assets/Lizzard.png'))),
                                    )),
                                TextButton(
                                    onPressed: () {},
                                    child: Container(
                                      height: 30,
                                      width: 25,
                                      decoration: const BoxDecoration(
                                          image: DecorationImage(
                                              fit: BoxFit.fill,
                                              image: AssetImage(
                                                  'assets/Mammal.png'))),
                                    )),
                                TextButton(
                                    onPressed: () {},
                                    child: Container(
                                      height: 30,
                                      width: 25,
                                      decoration: const BoxDecoration(
                                          image: DecorationImage(
                                              fit: BoxFit.fill,
                                              image: AssetImage(
                                                  'assets/Worm.png'))),
                                    )),
                              ]),
                        ),
                      ),
                      SizedBox(
                        child: LayoutBuilder(
                          builder: (BuildContext context,
                              BoxConstraints constraints) {
                            return GridView.builder(
                              shrinkWrap: true,
                              physics: const NeverScrollableScrollPhysics(),
                              gridDelegate:
                                  const SliverGridDelegateWithFixedCrossAxisCount(
                                crossAxisCount: 2,
                                childAspectRatio: 100 / 140,
                              ),
                              itemCount: state.list.length,
                              itemBuilder: (BuildContext context, int index) {
                                return Padding(
                                  padding:
                                      const EdgeInsets.symmetric(vertical: 8),
                                  child: EncounterItem(
                                    encounterResponse: state.list[index],
                                  ),
                                );
                              },
                            );
                          },
                        ),
                      ),
                    ],
                  ),
                ),
              );
            } else if (state is EncounterError) {
              return Text(state.errorMessage);
            }
            return const Center(child: CircularProgressIndicator());
          },
        ),
      ),
    );
  }
}
