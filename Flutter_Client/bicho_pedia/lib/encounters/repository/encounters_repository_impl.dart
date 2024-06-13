import 'dart:convert';
import 'dart:io';

import 'package:bicho_pedia/encounters/model/encounter_detail_response.dart';
import 'package:bicho_pedia/encounters/model/encounter_response.dart';
import 'package:bicho_pedia/encounters/model/encounter_simple_response.dart';
import 'package:bicho_pedia/encounters/model/file_response.dart';
import 'package:bicho_pedia/encounters/model/markes_response.dart';
import 'package:bicho_pedia/encounters/model/new_encounter_dto.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';

import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart';

class EncountersRepositoryImpl extends EncountersRepository {
  final Client _httpClient = Client();

  @override
  Future<List<EncounterSimpleResponse>> getMostLikedEncounters(
      int count, int page) async {
    final SharedPreferences _prefs = await SharedPreferences.getInstance();
    final String? token = _prefs.getString('token');

    final response = await _httpClient.get(
        Uri.parse(
            "http://10.0.2.2:8080/user/encounters/most-liked/simple?c=$count&p=$page"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        });

    if (response.statusCode == 200) {
      List<dynamic> jsonResponse = json.decode(response.body);
      return jsonResponse
          .map((map) => EncounterSimpleResponse.fromJson(map))
          .toList();
    } else {
      throw Exception('Falied to fetch data');
    }
  }

  @override
  Future<List<EncounterResponse>> getEncounters(
      int count, int page, String search) async {
    final SharedPreferences _prefs = await SharedPreferences.getInstance();
    final String? token = _prefs.getString('token');

    final response = await _httpClient.get(
        Uri.parse(
            "http://10.0.2.2:8080/user/encounters/allencounters?c=$count&p=$page"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        });
    if (response.statusCode == 200) {
      List<dynamic> jsonResponse = json.decode(response.body);
      return jsonResponse
          .map((map) => EncounterResponse.fromJson(map))
          .toList();
    } else {
      throw Exception('Failed to Fetch data');
    }
  }

  @override
  Future<List<MarkersResponse>> getMarkers() async {
    final SharedPreferences _prefs = await SharedPreferences.getInstance();
    final String? token = _prefs.getString('token');

    final response = await _httpClient.get(
        Uri.parse("http://10.0.2.2:8080/user/encounters/allmarkers"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        });

    if (response.statusCode == 200) {
      List<dynamic> jsonResponse = json.decode(response.body);
      return jsonResponse.map((map) => MarkersResponse.fromJson(map)).toList();
    } else {
      throw Exception('Failed to Fetch data');
    }
  }

  @override
  Future<EncounterDetailsResponse> getEncounterResponse(String id) async {
    final SharedPreferences _prefs = await SharedPreferences.getInstance();
    final String? token = _prefs.getString('token');

    final response = await _httpClient.get(
        Uri.parse("http://10.0.2.2:8080/user/encounters/encounterdetails/$id"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        });

    if (response.statusCode == 200) {
      return EncounterDetailsResponse.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to Fetch data');
    }
  }

  @override
  Future<NewEncounterDTO> newEncounter(NewEncounterDTO newEncounterDTO) async {
    final SharedPreferences _prefs = await SharedPreferences.getInstance();
    final String? token = _prefs.getString('token');

    final response = await _httpClient.post(
        Uri.parse("http://10.0.2.2:8080/user/encounters/find/"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        },
        body: jsonEncode(newEncounterDTO.toJson()));

    if (response.statusCode == 201) {
      return NewEncounterDTO.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to Register');
    }
  }

  @override
  Future<List<FileResponse>> uploadFiles(List<File> files) {
    throw UnimplementedError();
  }
}
