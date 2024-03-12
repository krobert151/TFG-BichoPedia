import 'package:bicho_pedia/species/bloc/specie_simple/specie_simple_bloc.dart';
import 'package:bicho_pedia/species/repositories/species_repository.dart';
import 'package:bicho_pedia/species/repositories/species_repository_impl.dart';
import 'package:bicho_pedia/species/widgets/simple_species_item.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class SimpleSpeciesLists extends StatefulWidget {
  const SimpleSpeciesLists({super.key});

  @override
  State<SimpleSpeciesLists> createState() => _SimpleSpeciesListsState();
}

class _SimpleSpeciesListsState extends State<SimpleSpeciesLists> {
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
          SpecieSimpleBloc(specieRepository)..add(DoSpecieSimpleEvent(10, 0)),
      child: _simpleSpeciesList(),
    );
  }

  _simpleSpeciesList() {
    return BlocBuilder<SpecieSimpleBloc, SpecieSimpleState>(
      builder: (BuildContext context, SpecieSimpleState state) {
        if (state is SpecieSimpleSuccess) {
          return Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Padding(
                padding: EdgeInsets.fromLTRB(10, 0, 10, 10),
                child: Text('Species in danger of extinction',
                    style: TextStyle(
                        fontFamily: 'OpenSans',
                        color: Colors.white,
                        fontWeight: FontWeight.bold)),
              ),
              SizedBox(
                height: 260,
                child: ListView.builder(
                  scrollDirection: Axis.horizontal,
                  itemCount: state.list.length,
                  itemBuilder: (context, index) {
                    return SimpleSpeciesItem(specieResponse: state.list[index]);
                  },
                ),
              ),
            ],
          );
        } else if (state is SpecieSimpleError) {
          return Text(state.errorMessage);
        }
        return const Center(child: CircularProgressIndicator());
      },
    );
  }
}
