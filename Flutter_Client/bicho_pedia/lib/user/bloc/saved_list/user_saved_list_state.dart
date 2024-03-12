part of 'user_saved_list_bloc.dart';

@immutable
sealed class UserSavedListState {}

final class UserSavedListInitial extends UserSavedListState {}

final class UserSavedListSuccess extends UserSavedListState {
  final List<UserSavedListResponse> list;
  UserSavedListSuccess(this.list);
}

final class UserSavedListError extends UserSavedListState {
  final String errorMessage;
  UserSavedListError(this.errorMessage);
}
