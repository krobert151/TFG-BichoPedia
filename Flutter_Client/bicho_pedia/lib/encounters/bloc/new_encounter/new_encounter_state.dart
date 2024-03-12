part of 'new_encounter_bloc.dart';

@immutable
sealed class NewEncounterState {}

final class NewEncounterInitial extends NewEncounterState {}

final class NewEncounterSucess extends NewEncounterState {
  final NewEncounterDTO encounterDTO;
  NewEncounterSucess(this.encounterDTO);
}

final class NewEncounterError extends NewEncounterState {
  final String errorMsg;
  NewEncounterError(this.errorMsg);
}
