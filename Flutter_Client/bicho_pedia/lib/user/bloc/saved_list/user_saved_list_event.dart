part of 'user_saved_list_bloc.dart';

@immutable
sealed class UserSavedListEvent {}

class DoUserSavedListEvent extends UserSavedListEvent {
  final String id;
  DoUserSavedListEvent(this.id);
}
