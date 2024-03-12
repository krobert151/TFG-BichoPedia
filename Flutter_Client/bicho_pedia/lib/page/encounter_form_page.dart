import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository_impl.dart';
import 'package:bicho_pedia/encounters/widgets/encounter_form.dart';
import 'package:bicho_pedia/species/bloc/all_specie_name/all_specie_names_bloc.dart';
import 'package:bicho_pedia/species/repositories/species_repository.dart';
import 'package:bicho_pedia/species/repositories/species_repository_impl.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class EncounterFormPage extends StatefulWidget {
  const EncounterFormPage({super.key});

  @override
  State<EncounterFormPage> createState() => _EncounterFormPageState();
}

class _EncounterFormPageState extends State<EncounterFormPage> {
  late EncountersRepository encountersRepository;
  late SpecieRepository specieRepository;

  @override
  void initState() {
    encountersRepository = EncountersRepositoryImpl();
    specieRepository = SpecieRepositoryImpl();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        height: double.infinity,
        width: double.infinity,
        decoration: const BoxDecoration(color: Colors.white),
        child: BlocProvider(
          create: (context) => AllSpecieNamesBloc(specieRepository)
            ..add(DoAllSpecieNamesEvent()),
          child: BlocBuilder<AllSpecieNamesBloc, AllSpecieNamesState>(
            builder: (BuildContext context, AllSpecieNamesState state) {
              if (state is AllSpecieNamesSuccess) {
                return EncounterFormItem(list: state.names);
              } else if (state is AllSpecieNamesError) {}
              return const Center(child: CircularProgressIndicator());
            },
          ),
        ),
      ),
    );
  }
}
