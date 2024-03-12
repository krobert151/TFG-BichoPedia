import 'package:bicho_pedia/user/widgets/user_encounters.dart';
import 'package:bicho_pedia/user/widgets/user_info.dart';
import 'package:bicho_pedia/user/widgets/user_saved_list.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class UserPage extends StatefulWidget {
  const UserPage({super.key});

  @override
  State<UserPage> createState() => _UserPageState();
}

class _UserPageState extends State<UserPage> {
  late Future<String> _id;

  @override
  void initState() {
    super.initState();
    _id = _loadPreferences();
  }

  Future<String> _loadPreferences() async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString('id')!;
  }

  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
        UserInfo(
          id: _id.toString(),
        ),
        UserSavedList(
          id: _id.toString(),
        ),
        UserEncounters(id: _id.toString())
      ],
    );
  }
}
