part of 'specie_details_bloc.dart';

@immutable
sealed class SpecieDetailsEvent {}

class DoSpecieDetailsEvent extends SpecieDetailsEvent {
  final String id;

  DoSpecieDetailsEvent(this.id);
}
