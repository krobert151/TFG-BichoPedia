import 'package:bicho_pedia/encounters/widgets/encounter_item.dart';
import 'package:bicho_pedia/user/bloc/user_encounters/user_encounters_bloc.dart';
import 'package:bicho_pedia/user/repositopry/user_repository.dart';
import 'package:bicho_pedia/user/repositopry/user_repository_impl.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class UserEncounters extends StatefulWidget {
  final String id;
  const UserEncounters({super.key, required this.id});

  @override
  State<UserEncounters> createState() => _UserEncountersState();
}

class _UserEncountersState extends State<UserEncounters> {
  late UserRepository userRepository;

  @override
  void initState() {
    super.initState();
    userRepository = UserRepositoryImpl();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) => UserEncountersBloc(userRepository)
          ..add(DoUserEncountersEvent(widget.id)),
        child: BlocBuilder<UserEncountersBloc, UserEncountersState>(
          builder: (BuildContext context, UserEncountersState state) {
            if (state is UserEncountersSuccess) {
              return Padding(
                padding: const EdgeInsets.all(8.0),
                child: SizedBox(
                  height: 270,
                  width: double.infinity,
                  child: ListView.builder(
                    scrollDirection: Axis.horizontal,
                    itemCount: state.list.length,
                    itemBuilder: (context, index) {
                      return EncounterItem(
                          encounterResponse: state.list[index]);
                    },
                  ),
                ),
              );
            } else if (state is UserEncountersError) {
              return Text(state.errorMessage,
                  style: const TextStyle(color: Colors.white));
            }
            return const Center(child: CircularProgressIndicator());
          },
        ));
  }
}
