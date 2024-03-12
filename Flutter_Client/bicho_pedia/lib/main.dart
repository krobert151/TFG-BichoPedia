import 'package:bicho_pedia/page/open_page.dart';
import 'package:bicho_pedia/species/bloc/specie/specie_bloc.dart';
import 'package:bicho_pedia/species/repositories/species_repository_impl.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

void main() {
  runApp(
    MultiBlocProvider(
      providers: [
        BlocProvider<SpecieBloc>(
          create: (context) => SpecieBloc(SpecieRepositoryImpl()),
        ),
      ],
      child: MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
            seedColor: const Color.fromARGB(255, 255, 255, 255)),
        useMaterial3: true,
      ),
      home: const Scaffold(body: OpenApp()),
    );
  }
}
