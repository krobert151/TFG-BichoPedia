import 'package:bicho_pedia/auth/models/login_dto.dart';
import 'package:bicho_pedia/auth/models/login_response.dart';
import 'package:bicho_pedia/auth/models/register_dto.dart';

abstract class AuthRepository {
  Future<LoginResponse> login(LoginDto loginDto);
  Future register(RegisterDto registerDto);
}
