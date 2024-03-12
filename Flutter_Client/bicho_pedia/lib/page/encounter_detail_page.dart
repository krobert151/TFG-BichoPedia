import 'package:bicho_pedia/encounters/bloc/encounter_detail/encounter_details_bloc.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository_impl.dart';
import 'package:bicho_pedia/encounters/widgets/encounter_details.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class EncounterDetailsPage extends StatefulWidget {
  final String id;

  const EncounterDetailsPage({super.key, required this.id});

  @override
  State<EncounterDetailsPage> createState() => _EncounterDetailsPageState();
}

class _EncounterDetailsPageState extends State<EncounterDetailsPage> {
  late EncountersRepository encounterRepository;

  late Container container = Container();

  @override
  void initState() {
    super.initState();
    encounterRepository = EncountersRepositoryImpl();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => EncounterDetailsBloc(encounterRepository)
        ..add(DoEncounterDetailsEvent(widget.id)),
      child: Scaffold(
        backgroundColor: const Color.fromARGB(255, 19, 20, 13),
        body: BlocBuilder<EncounterDetailsBloc, EncounterDetailsState>(
          builder: (BuildContext context, EncounterDetailsState state) {
            if (state is EncounterDetailsSuccess) {
              return EncounterDetailsWidget(encounter: state.encounterDetails);
            } else if (state is EncounterDetailsError) {
              return Text(state.errorMessage);
            }
            return const Center(child: CircularProgressIndicator());
          },
        ),
      ),
    );
  }
}
