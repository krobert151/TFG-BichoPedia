part of 'specie_simple_bloc.dart';

@immutable
sealed class SpecieSimpleState {}

final class SpecieSimpleInitial extends SpecieSimpleState {}

final class SpecieSimpleSuccess extends SpecieSimpleState {
  final List<SpeciesSimpleResponse> list;
  SpecieSimpleSuccess(this.list);
}

final class SpecieSimpleError extends SpecieSimpleState {
  final String errorMessage;
  SpecieSimpleError(this.errorMessage);
}
