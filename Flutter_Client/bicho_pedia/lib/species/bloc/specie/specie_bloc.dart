import 'package:bicho_pedia/species/model/specie_response.dart';
import 'package:bicho_pedia/species/repositories/species_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'specie_event.dart';
part 'specie_state.dart';

class SpecieBloc extends Bloc<SpecieEvent, SpecieState> {
  final SpecieRepository specieRepository;

  SpecieBloc(this.specieRepository) : super(SpecieInitial()) {
    on<DoSpecieEvent>(_getSpeciesList);
  }

  void _getSpeciesList(DoSpecieEvent event, Emitter<SpecieState> emit) async {
    try {
      final response = await specieRepository.getSpeciesLists(
          event.count, event.page, event.search);
      emit(SpecieSuccess(response));
    } on Exception catch (e) {
      emit(SpecieError(e.toString()));
    }
  }
}
