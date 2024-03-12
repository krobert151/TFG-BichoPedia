part of 'encounter_simple_bloc.dart';

@immutable
sealed class EncounterSimpleEvent {}

class DoEncounterSimpleEvent extends EncounterSimpleEvent {
  final int count;
  final int page;

  DoEncounterSimpleEvent(this.count, this.page);
}
