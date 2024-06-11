import 'dart:convert';

import 'package:bicho_pedia/encounters/model/encounter_response.dart';
import 'package:bicho_pedia/user/model/user_details_response.dart';
import 'package:bicho_pedia/user/model/user_saved_list_response.dart';
import 'package:bicho_pedia/user/repositopry/user_repository.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

class UserRepositoryImpl extends UserRepository {
  final Client _httpClient = Client();

  @override
  Future<UserDetailsResponse> findUserDetail(String id) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();

    final String? token = prefs.getString('token');
    final String? id = prefs.getString('id');

    final response = await _httpClient
        .get(Uri.parse("http://10.0.2.2:8080/user/userData/$id"), headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer $token',
    });

    if (response.statusCode == 200) {
      return UserDetailsResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to Fecth data');
    }
  }

  @override
  Future<List<UserSavedListResponse>> getUserSavedList(String id) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();

    final String? token = prefs.getString('token');
    final String? id = prefs.getString('id');

    final resposne = await _httpClient.get(
        Uri.parse("http://10.0.2.2:8080/user/userData/savedlist/$id"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        });

    if (resposne.statusCode == 200) {
      List<dynamic> jsonResponse = json.decode(resposne.body);
      return jsonResponse
          .map((map) => UserSavedListResponse.fromJson(map))
          .toList();
    } else {
      throw Exception('Failed to Fecth data');
    }
  }

  @override
  Future<List<EncounterResponse>> getUserEncounters(String id) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();

    final String? token = prefs.getString('token');
    final String? id = prefs.getString('id');

    final repsonse = await _httpClient.get(
        Uri.parse("http://10.0.2.2:8080/encounters/myencounters/$id"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        });

    if (repsonse.statusCode == 200) {
      List<dynamic> jsonResponse = json.decode(repsonse.body);
      return jsonResponse
          .map((map) => EncounterResponse.fromJson(map))
          .toList();
    } else {
      throw Exception('Failed to Fecth data');
    }
  }
}
