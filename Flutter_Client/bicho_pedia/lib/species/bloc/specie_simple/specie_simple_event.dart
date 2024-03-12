part of 'specie_simple_bloc.dart';

@immutable
sealed class SpecieSimpleEvent {}

class DoSpecieSimpleEvent extends SpecieSimpleEvent {
  final int count;
  final int page;

  DoSpecieSimpleEvent(this.count, this.page);
}
