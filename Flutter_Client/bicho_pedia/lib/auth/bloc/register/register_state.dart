part of 'register_bloc.dart';

@immutable
sealed class RegisterState {}

final class RegisterInitial extends RegisterState {}

final class DoRegisterSuccess extends RegisterState {
  final LoginResponse userLogin;
  DoRegisterSuccess(this.userLogin);
}

final class DoRegisterErrror extends RegisterState {
  final String errorMessage;
  DoRegisterErrror(this.errorMessage);
}
