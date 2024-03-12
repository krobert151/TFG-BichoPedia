import 'package:bicho_pedia/encounters/model/encounter_detail_response.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'encounter_details_event.dart';
part 'encounter_details_state.dart';

class EncounterDetailsBloc
    extends Bloc<EncounterDetailsEvent, EncounterDetailsState> {
  final EncountersRepository encountersRepository;

  EncounterDetailsBloc(this.encountersRepository)
      : super(EncounterDetailsInitial()) {
    on<DoEncounterDetailsEvent>(_getEncountersDetail);
  }

  void _getEncountersDetail(DoEncounterDetailsEvent event,
      Emitter<EncounterDetailsState> emit) async {
    try {
      final response =
          await encountersRepository.getEncounterResponse(event.id);
      emit(EncounterDetailsSuccess(response));
    } on Exception catch (e) {
      emit(EncounterDetailsError(e.toString()));
    }
  }
}
