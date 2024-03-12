import 'dart:convert';

import 'package:bicho_pedia/auth/models/login_dto.dart';
import 'package:bicho_pedia/auth/models/login_response.dart';
import 'package:bicho_pedia/auth/models/register_dto.dart';
import 'package:bicho_pedia/auth/repositories/auth_repository.dart';
import 'package:http/http.dart';

class AuthRepositoryImpl extends AuthRepository {
  final Client _httpClient = Client();

  @override
  Future<LoginResponse> login(LoginDto loginDto) async {
    final response =
        await _httpClient.post(Uri.parse("http://10.0.2.2:8080/auth/login"),
            headers: <String, String>{
              'Content-Type': 'application/json',
            },
            body: jsonEncode(loginDto.toJson()));
    if (response.statusCode == 201) {
      return LoginResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to do login');
    }
  }

  @override
  Future<LoginResponse> register(RegisterDto registerDto) async {
    final response =
        await _httpClient.post(Uri.parse("http://10.0.2.2:8080/auth/register"),
            headers: <String, String>{
              'Content-Type': 'application/json',
            },
            body: jsonEncode(registerDto.toJson()));
    if (response.statusCode == 201) {
      return LoginResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to Register');
    }
  }
}
