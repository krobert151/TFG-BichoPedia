import 'package:bicho_pedia/user/model/user_saved_list_response.dart';
import 'package:bicho_pedia/user/repositopry/user_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'user_saved_list_event.dart';
part 'user_saved_list_state.dart';

class UserSavedListBloc extends Bloc<UserSavedListEvent, UserSavedListState> {
  final UserRepository userRepository;

  UserSavedListBloc(this.userRepository) : super(UserSavedListInitial()) {
    on<DoUserSavedListEvent>(_getUserSavedList);
  }

  void _getUserSavedList(
      DoUserSavedListEvent event, Emitter<UserSavedListState> emit) async {
    try {
      final response = await userRepository.getUserSavedList(event.id);
      emit(UserSavedListSuccess(response));
    } on Exception catch (e) {
      emit(UserSavedListError(e.toString()));
    }
  }
}
