import 'package:bicho_pedia/user/bloc/user/user_details_bloc.dart';
import 'package:bicho_pedia/user/repositopry/user_repository.dart';
import 'package:bicho_pedia/user/repositopry/user_repository_impl.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:gap/gap.dart';
import 'package:percent_indicator/linear_percent_indicator.dart';

class UserInfo extends StatefulWidget {
  final String id;

  const UserInfo({super.key, required this.id});

  @override
  State<UserInfo> createState() => _UserInfoState();
}

class _UserInfoState extends State<UserInfo> {
  late UserRepository userRepository;

  @override
  void initState() {
    super.initState();
    userRepository = UserRepositoryImpl();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) => UserDetailsBloc(userRepository)
          ..add(DoUserDetailsEvent(widget.id.toString())),
        child: BlocBuilder<UserDetailsBloc, UserDetailsState>(
          builder: (BuildContext context, UserDetailsState state) {
            if (state is UserDetailsSuccess) {
              return Container(
                alignment: Alignment.bottomCenter,
                height: 400,
                decoration: BoxDecoration(
                  borderRadius: const BorderRadius.only(
                      topLeft: Radius.circular(20),
                      topRight: Radius.circular(20)),
                  image: DecorationImage(
                    alignment: Alignment.topCenter,
                    fit: BoxFit.cover,
                    image: NetworkImage(state.response.userPhoto!),
                  ),
                ),
                child: Container(
                  padding: const EdgeInsets.only(bottom: 30),
                  alignment: Alignment.bottomCenter,
                  decoration: const BoxDecoration(
                    borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(24),
                        topRight: Radius.circular(24)),
                    gradient: LinearGradient(
                      begin: Alignment.topCenter,
                      end: Alignment.bottomCenter,
                      stops: [0.35, 0.95],
                      colors: [
                        Color.fromARGB(0, 0, 0, 0),
                        Color.fromARGB(233, 0, 0, 0),
                      ],
                    ),
                  ),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      Text(
                        state.response.username!,
                        style: const TextStyle(
                            fontFamily: 'OpenSans',
                            color: Colors.white,
                            fontWeight: FontWeight.w600,
                            fontSize: 28),
                      ),
                      Text(
                        state.response.email!,
                        style: const TextStyle(
                            fontFamily: 'OpenSans',
                            color: Colors.white,
                            fontWeight: FontWeight.w400,
                            fontSize: 12),
                      ),
                      const Gap(20),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(state.response.encounters.toString(),
                                  style: const TextStyle(
                                      fontFamily: 'OpenSans',
                                      color: Colors.white,
                                      fontWeight: FontWeight.w600,
                                      fontSize: 20)),
                              const Text('Encounters',
                                  style: TextStyle(
                                      fontFamily: 'OpenSans',
                                      color: Colors.white,
                                      fontWeight: FontWeight.w400,
                                      fontSize: 12)),
                            ],
                          ),
                          Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(state.response.level.toString(),
                                  style: const TextStyle(
                                      fontFamily: 'OpenSans',
                                      color: Colors.white,
                                      fontWeight: FontWeight.w600,
                                      fontSize: 20)),
                              const Text('Level',
                                  style: TextStyle(
                                      fontFamily: 'OpenSans',
                                      color: Colors.white,
                                      fontWeight: FontWeight.w400,
                                      fontSize: 12)),
                            ],
                          ),
                          Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(state.response.articles.toString(),
                                  style: const TextStyle(
                                      fontFamily: 'OpenSans',
                                      color: Colors.white,
                                      fontWeight: FontWeight.w600,
                                      fontSize: 20)),
                              const Text('Articles',
                                  style: TextStyle(
                                      fontFamily: 'OpenSans',
                                      color: Colors.white,
                                      fontWeight: FontWeight.w400,
                                      fontSize: 12)),
                            ],
                          ),
                        ],
                      ),
                      const Gap(15),
                      Container(
                        width: 350,
                        decoration: BoxDecoration(
                          gradient: const LinearGradient(
                            begin: Alignment.centerLeft,
                            end: Alignment.centerRight,
                            stops: [0.0, 0.95],
                            colors: [
                              Color.fromARGB(255, 190, 222, 97),
                              Color.fromARGB(255, 0, 0, 0),
                            ],
                          ),
                          border: Border.all(
                            color: Colors.white,
                            width: 0.5,
                          ),
                          borderRadius: BorderRadius.circular(50),
                        ),
                        child: LinearPercentIndicator(
                          lineHeight: 4,
                          backgroundColor: const Color.fromARGB(255, 0, 0, 0),
                          progressColor:
                              const Color.fromARGB(255, 190, 222, 97),
                          percent: double.parse(
                              (state.response.percentExp! / 100).toString()),
                          alignment: MainAxisAlignment.start,
                          width: 330,
                        ),
                      ),
                    ],
                  ),
                ),
              );
            } else if (state is UserDetailsError) {
              return Text(state.errorMessage);
            }
            return const Center(child: CircularProgressIndicator());
          },
        ));
  }
}
