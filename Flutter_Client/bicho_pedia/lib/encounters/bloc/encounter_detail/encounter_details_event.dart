part of 'encounter_details_bloc.dart';

@immutable
sealed class EncounterDetailsEvent {}

class DoEncounterDetailsEvent extends EncounterDetailsEvent {
  final String id;
  DoEncounterDetailsEvent(this.id);
}
