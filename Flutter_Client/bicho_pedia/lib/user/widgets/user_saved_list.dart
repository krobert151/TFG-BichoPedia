import 'package:bicho_pedia/user/bloc/saved_list/user_saved_list_bloc.dart';
import 'package:bicho_pedia/user/repositopry/user_repository.dart';
import 'package:bicho_pedia/user/repositopry/user_repository_impl.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class UserSavedList extends StatefulWidget {
  final String id;
  const UserSavedList({super.key, required this.id});

  @override
  State<UserSavedList> createState() => _UserSavedListState();
}

class _UserSavedListState extends State<UserSavedList> {
  late UserRepository userRepository;

  @override
  void initState() {
    super.initState();
    userRepository = UserRepositoryImpl();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) => UserSavedListBloc(userRepository)
          ..add(DoUserSavedListEvent(widget.id)),
        child: BlocBuilder<UserSavedListBloc, UserSavedListState>(
          builder: (BuildContext context, UserSavedListState state) {
            if (state is UserSavedListSuccess) {
              return Padding(
                padding: const EdgeInsets.all(8.0),
                child: Container(
                  height: 150,
                  width: double.infinity,
                  child: ListView.builder(
                    scrollDirection: Axis.horizontal,
                    itemCount: state.list.length,
                    itemBuilder: (context, index) {
                      return TextButton(
                        onPressed: () {},
                        child: Container(
                          width: 150,
                          height: 150,
                          decoration: BoxDecoration(
                              borderRadius:
                                  const BorderRadius.all(Radius.circular(20)),
                              image: DecorationImage(
                                  fit: BoxFit.cover,
                                  image:
                                      NetworkImage(state.list[index].photo!))),
                          child: Container(
                              padding: const EdgeInsets.only(bottom: 15),
                              alignment: Alignment.bottomCenter,
                              decoration: const BoxDecoration(
                                  borderRadius:
                                      BorderRadius.all(Radius.circular(20)),
                                  color: Color.fromARGB(95, 0, 0, 0)),
                              child: Text(
                                state.list[index].name!,
                                style: const TextStyle(
                                    color: Colors.white,
                                    fontFamily: 'OpenSans',
                                    fontWeight: FontWeight.w700),
                              )),
                        ),
                      );
                    },
                  ),
                ),
              );
            } else if (state is UserSavedListError) {
              return Text(state.errorMessage,
                  style: const TextStyle(color: Colors.white));
            }
            return const Center(child: CircularProgressIndicator());
          },
        ));
  }
}
