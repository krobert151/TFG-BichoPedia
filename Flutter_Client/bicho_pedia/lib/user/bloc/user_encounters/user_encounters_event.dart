part of 'user_encounters_bloc.dart';

@immutable
sealed class UserEncountersEvent {}

class DoUserEncountersEvent extends UserEncountersEvent {
  final String id;

  DoUserEncountersEvent(this.id);
}
