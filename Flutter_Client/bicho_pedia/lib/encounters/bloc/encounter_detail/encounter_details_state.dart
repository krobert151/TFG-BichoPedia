part of 'encounter_details_bloc.dart';

@immutable
sealed class EncounterDetailsState {}

final class EncounterDetailsInitial extends EncounterDetailsState {}

final class EncounterDetailsSuccess extends EncounterDetailsState {
  final EncounterDetailsResponse encounterDetails;
  EncounterDetailsSuccess(this.encounterDetails);
}

final class EncounterDetailsError extends EncounterDetailsState {
  final String errorMessage;
  EncounterDetailsError(this.errorMessage);
}
