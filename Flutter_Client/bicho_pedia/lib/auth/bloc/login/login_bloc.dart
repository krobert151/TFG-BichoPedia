import 'package:bicho_pedia/auth/models/login_dto.dart';
import 'package:bicho_pedia/auth/models/login_response.dart';
import 'package:bicho_pedia/auth/repositories/auth_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';
import 'package:meta/meta.dart';
import 'package:shared_preferences/shared_preferences.dart';

part 'login_event.dart';
part 'login_state.dart';

class LoginBloc extends Bloc<LoginEvent, LoginState> {
  final AuthRepository authRepository;
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();

  LoginBloc(this.authRepository) : super(LoginInitial()) {
    on<DoLoginEvent>(_doLogin);
  }

  void _doLogin(DoLoginEvent event, Emitter<LoginState> emit) async {
    final SharedPreferences prefs = await _prefs;

    try {
      final LoginDto loginDto = LoginDto(
        username: event.username,
        password: event.password,
      );
      final response = await authRepository.login(loginDto);
      prefs.setString('token', response.token!);
      prefs.setString('username', response.username!);
      prefs.setString('id', response.id!);

      emit(DoLoginSuccess(response));
    } on Exception catch (e) {
      emit(DoLoginError(e.toString()));
    }
  }
}
