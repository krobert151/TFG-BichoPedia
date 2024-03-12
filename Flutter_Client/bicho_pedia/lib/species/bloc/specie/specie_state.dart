part of 'specie_bloc.dart';

@immutable
sealed class SpecieState {}

final class SpecieInitial extends SpecieState {}

final class SpecieSuccess extends SpecieState {
  final List<SpeciesResponse> list;
  SpecieSuccess(this.list);
}

final class SpecieError extends SpecieState {
  final String errorMessage;
  SpecieError(this.errorMessage);
}
