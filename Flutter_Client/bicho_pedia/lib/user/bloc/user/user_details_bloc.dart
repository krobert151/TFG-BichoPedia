import 'package:bicho_pedia/user/model/user_details_response.dart';
import 'package:bicho_pedia/user/repositopry/user_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'user_details_event.dart';
part 'user_details_state.dart';

class UserDetailsBloc extends Bloc<UserDetailsEvent, UserDetailsState> {
  final UserRepository userRepository;

  UserDetailsBloc(this.userRepository) : super(UserDetailsInitial()) {
    on<DoUserDetailsEvent>(_getUserDetail);
  }

  void _getUserDetail(
      DoUserDetailsEvent event, Emitter<UserDetailsState> emit) async {
    try {
      final response = await userRepository.findUserDetail(event.id);
      emit(UserDetailsSuccess(response));
    } on Exception catch (e) {
      emit(UserDetailsError(e.toString()));
    }
  }
}
