part of 'encounter_bloc.dart';

@immutable
sealed class EncounterEvent {}

class DoEncounterEvent extends EncounterEvent {
  final int count;
  final int page;
  final String search;

  DoEncounterEvent(this.count, this.page, this.search);
}
