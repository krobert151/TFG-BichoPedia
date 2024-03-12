part of 'all_specie_names_bloc.dart';

@immutable
sealed class AllSpecieNamesState {}

final class AllSpecieNamesInitial extends AllSpecieNamesState {}

final class AllSpecieNamesSuccess extends AllSpecieNamesState {
  final List<SpeciesNameResponse> names;
  AllSpecieNamesSuccess(this.names);
}

final class AllSpecieNamesError extends AllSpecieNamesState {
  final String errorMsg;

  AllSpecieNamesError(this.errorMsg);
}
