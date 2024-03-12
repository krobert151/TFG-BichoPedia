import 'package:bicho_pedia/species/repositories/species_repository.dart';
import 'package:bicho_pedia/species/widgets/specie_item.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:bicho_pedia/species/bloc/specie/specie_bloc.dart';
import 'package:bicho_pedia/species/repositories/species_repository_impl.dart';

class EncyClopediaPage extends StatefulWidget {
  const EncyClopediaPage({Key? key}) : super(key: key);

  @override
  State<EncyClopediaPage> createState() => _EncyClopediaPageState();
}

class _EncyClopediaPageState extends State<EncyClopediaPage> {
  late SpecieRepository specieRepository;

  @override
  void initState() {
    specieRepository = SpecieRepositoryImpl();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) =>
          SpecieBloc(specieRepository)..add(DoSpecieEvent(10, 0, '')),
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
                    hintText: 'Search Especies',
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
        body: BlocBuilder<SpecieBloc, SpecieState>(
          builder: (BuildContext context, SpecieState state) {
            if (state is SpecieInitial) {
              return const Text(
                'Cargando',
                style: TextStyle(
                  fontFamily: 'OpenSans',
                  color: Color.fromARGB(255, 255, 255, 255),
                ),
              );
            } else if (state is SpecieSuccess) {
              return Padding(
                padding: const EdgeInsets.symmetric(horizontal: 12),
                child: SingleChildScrollView(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Container(
                        height: 150,
                        width: 370,
                        decoration: const BoxDecoration(
                            borderRadius: BorderRadius.all(Radius.circular(24)),
                            image: DecorationImage(
                                fit: BoxFit.cover,
                                image: AssetImage('assets/SearchSpecies.png'))),
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
                            child: const Text('Encyclopedia',
                                style: TextStyle(
                                  fontFamily: 'OpenSans',
                                  fontWeight: FontWeight.w700,
                                  color: Color.fromARGB(255, 255, 255, 255),
                                )),
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
                      LayoutBuilder(
                        builder:
                            (BuildContext context, BoxConstraints constraints) {
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
                                child: SpeciesItem(
                                  specieResponse: state.list[index],
                                ),
                              );
                            },
                          );
                        },
                      ),
                    ],
                  ),
                ),
              );
            } else if (state is SpecieError) {
              return Text(state.errorMessage);
            }
            return const Center(child: CircularProgressIndicator());
          },
        ),
      ),
    );
  }
}
