part of 'encounter_simple_bloc.dart';

@immutable
sealed class EncounterSimpleState {}

final class EncounterSimpleInitial extends EncounterSimpleState {}

final class EncounterSimpleSuccess extends EncounterSimpleState {
  final List<EncounterSimpleResponse> list;
  EncounterSimpleSuccess(this.list);
}

final class EncounterSimpleError extends EncounterSimpleState {
  final String errorMessage;
  EncounterSimpleError(this.errorMessage);
}
