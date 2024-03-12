part of 'user_details_bloc.dart';

@immutable
sealed class UserDetailsState {}

final class UserDetailsInitial extends UserDetailsState {}

final class UserDetailsSuccess extends UserDetailsState {
  final UserDetailsResponse response;
  UserDetailsSuccess(this.response);
}

final class UserDetailsError extends UserDetailsState {
  final String errorMessage;
  UserDetailsError(this.errorMessage);
}
