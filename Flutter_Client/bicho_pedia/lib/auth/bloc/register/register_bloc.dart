import 'package:bicho_pedia/auth/models/login_response.dart';
import 'package:bicho_pedia/auth/models/register_dto.dart';
import 'package:bicho_pedia/auth/repositories/auth_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';
import 'package:shared_preferences/shared_preferences.dart';

part 'register_event.dart';
part 'register_state.dart';

class RegisterBloc extends Bloc<RegisterEvent, RegisterState> {
  final AuthRepository authRepository;

  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();

  RegisterBloc(this.authRepository) : super(RegisterInitial()) {
    on<DoRegisterEvent>(_doRegister);
  }

  void _doRegister(DoRegisterEvent event, Emitter<RegisterState> emit) async {
    final SharedPreferences prefs = await _prefs;

    try {
      final RegisterDto registerDto = RegisterDto(
          username: event.username,
          email: event.email,
          password: event.password);
      final response = await authRepository.register(registerDto);
      prefs.setString('token', response.token!);
      prefs.setString('username', response.username!);
      prefs.setString('id', response.id!);

      emit(DoRegisterSuccess(response));
    } on Exception catch (e) {
      emit(DoRegisterErrror(e.toString()));
    }
  }
}
