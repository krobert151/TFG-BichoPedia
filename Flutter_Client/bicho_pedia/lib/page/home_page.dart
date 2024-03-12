import 'package:bicho_pedia/encounters/widgets/simple_encounters_list.dart';
import 'package:bicho_pedia/species/widgets/simple_species_list.dart';
import 'package:flutter/material.dart';
import 'package:get/get_connect/http/src/utils/utils.dart';
import 'package:shared_preferences/shared_preferences.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  late Future<String> _usernameFuture;

  @override
  void initState() {
    super.initState();
    _usernameFuture = _loadPreferences();
  }

  Future<String> _loadPreferences() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString('username')!;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromARGB(255, 19, 20, 13),
      appBar: AppBar(
        automaticallyImplyLeading: F,
        toolbarHeight: 80,
        backgroundColor: const Color.fromARGB(255, 19, 20, 13),
        title: Container(
          margin: const EdgeInsets.all(10),
          alignment: Alignment.centerLeft,
          child: FutureBuilder<String>(
            future: _usernameFuture,
            builder: (BuildContext context, AsyncSnapshot<String> snapshot) {
              if (snapshot.hasData) {
                return Text(
                  'Hello ${snapshot.data}',
                  style: const TextStyle(
                      fontFamily: 'OpenSans', color: Colors.white),
                );
              } else {
                return const Center(child: CircularProgressIndicator());
              }
            },
          ),
        ),
      ),
      body: const Padding(
          padding: EdgeInsets.all(15),
          child: SizedBox(
            height: double.infinity,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [SimpleSpeciesLists(), SimpleEncountersList()],
            ),
          )),
    );
  }
}
