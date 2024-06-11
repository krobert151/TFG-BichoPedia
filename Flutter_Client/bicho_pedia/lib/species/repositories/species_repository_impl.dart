import 'dart:convert';

import 'package:bicho_pedia/species/model/specie_details_response.dart';
import 'package:bicho_pedia/species/model/specie_response.dart';
import 'package:bicho_pedia/species/model/species_name_resposne.dart';
import 'package:bicho_pedia/species/model/species_simple_response.dart';
import 'package:bicho_pedia/species/repositories/species_repository.dart';
import 'package:http/http.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SpecieRepositoryImpl extends SpecieRepository {
  final Client _httpClient = Client();

  @override
  Future<List<SpeciesSimpleResponse>> getDangerSpeciesSimple(
      int count, int page) async {
    final SharedPreferences _prefs = await SharedPreferences.getInstance();

    final String? token = _prefs.getString('token');
    final response = await _httpClient.get(
        Uri.parse(
            "http://10.0.2.2:8080/user/species/danger-extinction/simple?c=$count&p=$page"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        });
    if (response.statusCode == 200) {
      List<dynamic> jsonResponse = json.decode(response.body);
      return jsonResponse
          .map((map) => SpeciesSimpleResponse.fromJson(map))
          .toList();
    } else {
      throw Exception('Failed to fetch data');
    }
  }

  @override
  Future<List<SpeciesResponse>> getSpeciesLists(
      int count, int page, String specie) async {
    final SharedPreferences _prefs = await SharedPreferences.getInstance();
    final String? token = _prefs.getString('token');

    final response = await _httpClient.get(
        Uri.parse(
            "http://10.0.2.2:8080/user/species/allspecies?c=$count&p=$page"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        });
    if (response.statusCode == 200) {
      List<dynamic> jsonResponse = json.decode(response.body);
      return jsonResponse.map((map) => SpeciesResponse.fromJson(map)).toList();
    } else {
      throw Exception('Failed to Fecth data');
    }
  }

  @override
  Future<SpecieDetailsResponse> getSpecieDetailsById(String id) async {
    final SharedPreferences _prefs = await SharedPreferences.getInstance();
    final String? token = _prefs.getString('token');

    final response = await _httpClient.get(
        Uri.parse("http://10.0.2.2:8080/user/species/speciebyid/$id"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token',
        });
    if (response.statusCode == 200) {
      return SpecieDetailsResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to Fecth data');
    }
  }

  @override
  Future<List<SpeciesNameResponse>> getAllSpeciesName() async {
    final SharedPreferences _prefs = await SharedPreferences.getInstance();
    final String? token = _prefs.getString('token');

    final response = await _httpClient
        .get(Uri.parse("http://10.0.2.2:8080/user/species/names"), headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer $token',
    });

    if (response.statusCode == 200) {
      List<dynamic> jsonResponse = json.decode(response.body);
      return jsonResponse
          .map((map) => SpeciesNameResponse.fromJson(map))
          .toList();
    } else {
      throw Exception('Failed to Fecth data');
    }
  }
}
