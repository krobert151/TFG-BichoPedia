part of 'user_encounters_bloc.dart';

@immutable
sealed class UserEncountersState {}

final class UserEncountersInitial extends UserEncountersState {}

final class UserEncountersSuccess extends UserEncountersState {
  final List<EncounterResponse> list;
  UserEncountersSuccess(this.list);
}

final class UserEncountersError extends UserEncountersState {
  final String errorMessage;
  UserEncountersError(this.errorMessage);
}
