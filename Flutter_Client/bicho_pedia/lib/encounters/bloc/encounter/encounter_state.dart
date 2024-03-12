part of 'encounter_bloc.dart';

@immutable
sealed class EncounterState {}

final class EncounterInitial extends EncounterState {}

final class EncounterSuccess extends EncounterState {
  final List<EncounterResponse> list;
  EncounterSuccess(this.list);
}

final class EncounterError extends EncounterState {
  final String errorMessage;
  EncounterError(this.errorMessage);
}
