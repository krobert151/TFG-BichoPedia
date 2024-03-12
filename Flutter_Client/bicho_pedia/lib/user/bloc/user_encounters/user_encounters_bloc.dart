import 'package:bicho_pedia/encounters/model/encounter_response.dart';
import 'package:bicho_pedia/user/repositopry/user_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'user_encounters_event.dart';
part 'user_encounters_state.dart';

class UserEncountersBloc
    extends Bloc<UserEncountersEvent, UserEncountersState> {
  final UserRepository userRepository;
  UserEncountersBloc(this.userRepository) : super(UserEncountersInitial()) {
    on<DoUserEncountersEvent>(_getUserEncounters);
  }

  void _getUserEncounters(
      DoUserEncountersEvent event, Emitter<UserEncountersState> emit) async {
    try {
      final response = await userRepository.getUserEncounters(event.id);
      emit(UserEncountersSuccess(response));
    } on Exception catch (e) {
      emit(UserEncountersError(e.toString()));
    }
  }
}
