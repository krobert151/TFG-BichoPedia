import 'package:bicho_pedia/encounters/bloc/new_encounter/new_encounter_bloc.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository_impl.dart';
import 'package:bicho_pedia/page/menu_page.dart';
import 'package:bicho_pedia/species/model/species_name_resposne.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:gap/gap.dart';
import 'package:geolocator/geolocator.dart';

class EncounterFormItem extends StatefulWidget {
  final List<SpeciesNameResponse> list;

  const EncounterFormItem({Key? key, required this.list}) : super(key: key);

  @override
  _EncounterFormItemState createState() => _EncounterFormItemState();
}

class _EncounterFormItemState extends State<EncounterFormItem> {
  final _formEncounter = GlobalKey<FormState>();
  late String dropdownValue;
  late String location;
  final descriptionTextController = TextEditingController();
  final photoTextController = TextEditingController();

  late EncountersRepository encountersRepository;
  late NewEncounterBloc _encounterBloc;

  @override
  void initState() {
    _determinePosition().then((value) {
      location = value;
    });
    dropdownValue = widget.list.isNotEmpty ? widget.list[0].id! : '';
    encountersRepository = EncountersRepositoryImpl();
    _encounterBloc = NewEncounterBloc(encountersRepository);
    super.initState();
  }

  @override
  void dispose() {
    descriptionTextController.dispose();
    photoTextController.dispose();
    _encounterBloc.close();
    super.dispose();
  }

  Future<String> _determinePosition() async {
    bool serviceEnabled;
    LocationPermission permission;

    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      return Future.error('Location services are disabled.');
    }

    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        return Future.error('Location permissions are denied');
      }
    }

    if (permission == LocationPermission.deniedForever) {
      return Future.error(
          'Location permissions are permanently denied, we cannot request permissions.');
    }
    Position p = await Geolocator.getCurrentPosition();

    return '${p.latitude},${p.longitude}';
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider<NewEncounterBloc>.value(
      value: _encounterBloc,
      child: BlocConsumer<NewEncounterBloc, NewEncounterState>(
        buildWhen: (previous, state) {
          return state is NewEncounterSucess || state is NewEncounterError;
        },
        builder: (BuildContext context, NewEncounterState state) {
          if (state is NewEncounterSucess) {
            return const MenuScreen();
          } else if (state is NewEncounterError) {
            return const Text('Registration Error');
          }
          return Center(
            child: _buildEncounterForm(),
          );
        },
        listener: (BuildContext context, NewEncounterState state) {},
      ),
    );
  }

  Widget _buildEncounterForm() {
    return Container(
      color: const Color.fromRGBO(19, 20, 13, 1),
      height: double.infinity,
      width: double.infinity,
      padding: const EdgeInsets.fromLTRB(25, 0, 25, 0),
      alignment: Alignment.center,
      child: Padding(
        padding: const EdgeInsets.only(top: 50),
        child: Form(
          key: _formEncounter,
          child: ListView(
            padding: const EdgeInsets.symmetric(vertical: 16),
            children: [
              DropdownButton(
                dropdownColor: const Color.fromRGBO(19, 20, 13, 1),
                value: dropdownValue,
                onChanged: (value) {
                  setState(() {
                    dropdownValue = value!;
                  });
                },
                items: widget.list.map((e) {
                  return DropdownMenuItem(
                      value: e.id,
                      child: Text(
                        e.name!,
                        style: const TextStyle(
                          color: Colors.white,
                        ),
                      ));
                }).toList(),
              ),
              const Gap(16),
              SizedBox(
                height: 100,
                width: 300,
                child: TextFormField(
                  controller: descriptionTextController,
                  style: const TextStyle(color: Colors.white),
                  decoration: const InputDecoration(
                    enabledBorder: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(8)),
                      borderSide: BorderSide(
                        color: Color.fromARGB(255, 164, 164, 164),
                        width: 1,
                      ),
                    ),
                    labelText: 'Description',
                    labelStyle: TextStyle(
                      color: Color.fromARGB(255, 164, 164, 164),
                      fontWeight: FontWeight.w200,
                    ),
                    focusedBorder: OutlineInputBorder(
                      borderSide: BorderSide(
                        color: Color.fromARGB(255, 113, 113, 113),
                      ),
                    ),
                    floatingLabelBehavior: FloatingLabelBehavior.never,
                  ),
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter some text';
                    }
                    return null;
                  },
                ),
              ),
              const Gap(16),
              TextFormField(
                controller: photoTextController,
                style: const TextStyle(color: Colors.white),
                decoration: const InputDecoration(
                  enabledBorder: OutlineInputBorder(
                    borderRadius: BorderRadius.all(Radius.circular(8)),
                    borderSide: BorderSide(
                      color: Color.fromARGB(255, 164, 164, 164),
                      width: 1,
                    ),
                  ),
                  labelText: 'Url photo',
                  labelStyle: TextStyle(
                    color: Color.fromARGB(255, 164, 164, 164),
                    fontWeight: FontWeight.w200,
                  ),
                  focusedBorder: OutlineInputBorder(
                    borderSide: BorderSide(
                      color: Color.fromARGB(255, 113, 113, 113),
                    ),
                  ),
                  floatingLabelBehavior: FloatingLabelBehavior.never,
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter some text';
                  }
                  return null;
                },
              ),
              const Gap(16),
              Container(
                width: double.infinity,
                decoration: const BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(15)),
                  gradient: LinearGradient(
                    colors: [
                      Color.fromARGB(255, 190, 222, 97),
                      Color.fromARGB(255, 110, 128, 57),
                    ],
                  ),
                ),
                child: ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.transparent,
                    shadowColor: Colors.transparent,
                    shape: const RoundedRectangleBorder(
                      borderRadius: BorderRadius.all(Radius.circular(15)),
                    ),
                  ),
                  child: const Text(
                    'Registrar',
                    style: TextStyle(
                      fontFamily: 'OpenSans',
                      color: Colors.white,
                      fontSize: 20,
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                  onPressed: () {
                    if (_formEncounter.currentState!.validate()) {
                      _encounterBloc.add(
                        DoNewEncounterEvent(
                            dropdownValue,
                            descriptionTextController.text,
                            location,
                            [photoTextController.text]),
                      );
                    }
                  },
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
