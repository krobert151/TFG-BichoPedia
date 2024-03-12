import 'package:bicho_pedia/species/model/species_simple_response.dart';
import 'package:bicho_pedia/species/repositories/species_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'specie_simple_event.dart';
part 'specie_simple_state.dart';

class SpecieSimpleBloc extends Bloc<SpecieSimpleEvent, SpecieSimpleState> {
  final SpecieRepository specieSimpleRepository;

  SpecieSimpleBloc(this.specieSimpleRepository) : super(SpecieSimpleInitial()) {
    on<DoSpecieSimpleEvent>(_getSimpleSpecie);
  }

  void _getSimpleSpecie(
      DoSpecieSimpleEvent event, Emitter<SpecieSimpleState> emit) async {
    try {
      final response = await specieSimpleRepository.getDangerSpeciesSimple(
          event.count, event.page);
      emit(SpecieSimpleSuccess(response));
    } on Exception catch (e) {
      emit(SpecieSimpleError(e.toString()));
    }
  }
}
