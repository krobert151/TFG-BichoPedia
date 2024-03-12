part of 'user_details_bloc.dart';

@immutable
sealed class UserDetailsEvent {}

class DoUserDetailsEvent extends UserDetailsEvent {
  final String id;

  DoUserDetailsEvent(this.id);
}
