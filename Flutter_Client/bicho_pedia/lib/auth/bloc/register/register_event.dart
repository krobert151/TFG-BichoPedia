part of 'register_bloc.dart';

@immutable
sealed class RegisterEvent {}

class DoRegisterEvent extends RegisterEvent {
  final String username;
  final String email;
  final String password;

  DoRegisterEvent(this.username, this.email, this.password);
}
