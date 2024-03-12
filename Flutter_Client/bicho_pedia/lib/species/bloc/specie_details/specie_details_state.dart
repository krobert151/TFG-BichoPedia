part of 'specie_details_bloc.dart';

@immutable
sealed class SpecieDetailsState {}

final class SpecieDetailsInitial extends SpecieDetailsState {}

final class SpecieDetailsSuccess extends SpecieDetailsState {
  final SpecieDetailsResponse detail;
  SpecieDetailsSuccess(this.detail);
}

final class SpecieDetailsError extends SpecieDetailsState {
  final String errorMessage;
  SpecieDetailsError(this.errorMessage);
}
