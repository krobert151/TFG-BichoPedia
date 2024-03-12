part of 'specie_bloc.dart';

@immutable
sealed class SpecieEvent {}

class DoSpecieEvent extends SpecieEvent {
  final int count;
  final int page;
  final String search;

  DoSpecieEvent(this.count, this.page, this.search);
}
