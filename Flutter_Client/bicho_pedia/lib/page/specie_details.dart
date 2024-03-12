import 'package:bicho_pedia/species/bloc/specie_details/specie_details_bloc.dart';
import 'package:bicho_pedia/species/model/specie_details_response.dart';
import 'package:bicho_pedia/species/repositories/species_repository.dart';
import 'package:bicho_pedia/species/repositories/species_repository_impl.dart';
import 'package:bicho_pedia/species/widgets/specie_details_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class SpecieDetailsPage extends StatefulWidget {
  final String id;
  const SpecieDetailsPage({Key? key, required this.id}) : super(key: key);

  @override
  State<SpecieDetailsPage> createState() => _SpecieDetailsPageState();
}

class _SpecieDetailsPageState extends State<SpecieDetailsPage> {
  late SpecieRepository specieRepository;
  late List<Article> lists = [];

  @override
  void initState() {
    super.initState();
    specieRepository = SpecieRepositoryImpl();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => SpecieDetailsBloc(specieRepository)
        ..add(DoSpecieDetailsEvent(widget.id)),
      child: Scaffold(
        backgroundColor: const Color.fromARGB(255, 19, 20, 13),
        body: BlocBuilder<SpecieDetailsBloc, SpecieDetailsState>(
          builder: (BuildContext context, SpecieDetailsState state) {
            if (state is SpecieDetailsSuccess) {
              return SpecieDetailsWidget(specie: state.detail);
            } else if (state is SpecieDetailsError) {
              return Text(state.errorMessage);
            }
            return const CircularProgressIndicator.adaptive();
          },
        ),
      ),
    );
  }
}
