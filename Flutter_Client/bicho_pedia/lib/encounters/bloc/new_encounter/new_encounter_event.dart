part of 'new_encounter_bloc.dart';

@immutable
sealed class NewEncounterEvent {}

class DoNewEncounterEvent extends NewEncounterEvent {
  final String specieId;
  final String description;
  final String location;
  final List<String> photos;

  DoNewEncounterEvent(
      this.specieId, this.description, this.location, this.photos);
}
