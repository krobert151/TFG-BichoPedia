import 'package:bicho_pedia/species/model/specie_details_response.dart';
import 'package:bicho_pedia/species/repositories/species_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'specie_details_event.dart';
part 'specie_details_state.dart';

class SpecieDetailsBloc extends Bloc<SpecieDetailsEvent, SpecieDetailsState> {
  final SpecieRepository specieRepository;

  SpecieDetailsBloc(this.specieRepository) : super(SpecieDetailsInitial()) {
    on<DoSpecieDetailsEvent>(_getSpecieDetails);
  }

  void _getSpecieDetails(
      DoSpecieDetailsEvent event, Emitter<SpecieDetailsState> emit) async {
    try {
      final response = await specieRepository.getSpecieDetailsById(event.id);
      emit(SpecieDetailsSuccess(response));
    } on Exception catch (e) {
      emit(SpecieDetailsError(e.toString()));
    }
  }
}
