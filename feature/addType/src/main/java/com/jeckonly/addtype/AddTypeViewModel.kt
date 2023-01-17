package com.jeckonly.addtype

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.addtype.ui.state.AddTypeKind
import com.jeckonly.addtype.ui.state.AddTypeScreenState
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.entity.update.TypeUpdateNoOrder
import com.jeckonly.designsystem.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTypeViewModel @Inject constructor(
    private val app: Application,
    private val userPrefsRepo: UserPrefsRepo,
    private val databaseRepo: DatabaseRepo
) : ViewModel() {

    private val _addTypeScreenState: MutableStateFlow<AddTypeScreenState> =
        MutableStateFlow(AddTypeScreenState())

    val addTypeScreenState: StateFlow<AddTypeScreenState> = _addTypeScreenState

    val addTypeKinds = getAddTypeKinds(app)

    private var _typeId = -1


    fun initViewModel(typeId: Int) {
        this._typeId = typeId
        if (typeId == -1) return
        viewModelScope.launch {
            val typeEntity = databaseRepo.getTypeById(typeId)
            _addTypeScreenState.update {
                AddTypeScreenState(
                    iconId = typeEntity.iconId,
                    typeName = typeEntity.name,
                    expenseOrIncome = typeEntity.expenseOrIncome
                )
            }
        }
    }

    fun onClickIcon(id: Int) {
        _addTypeScreenState.update {
            it.copy(iconId = id)
        }
    }

    fun onNameChanged(newValue: String) {
        if (newValue.length > 15) return
        _addTypeScreenState.update {
            it.copy(typeName = newValue)
        }
    }

    fun onExpenseOrIncomeChanged(expenseOrIncome: ExpenseOrIncome) {
        _addTypeScreenState.update {
            it.copy(expenseOrIncome = expenseOrIncome)
        }
    }

    fun onSave(afterSave: () -> Unit) {
        viewModelScope.launch {
            val result = _addTypeScreenState.value
            if (result.typeName.isEmpty()) {
                Toast.makeText(app, app.getString(R.string.cant_empty_typeName), Toast.LENGTH_SHORT).show()
                return@launch
            }
            if (databaseRepo.isTypeNameExited(result.typeName)) {
                Toast.makeText(app, app.getString(R.string.name_existed), Toast.LENGTH_SHORT).show()
                return@launch
            }
            if (_typeId == -1) {
                // 插入新的
                val maxOrder: Int = databaseRepo.getMaxOrder()?:0
                databaseRepo.insertType(
                    TypeEntity(
                        name = result.typeName,
                        iconId = result.iconId,
                        order = maxOrder + 1,
                        expenseOrIncome = result.expenseOrIncome
                    )
                )
            } else {
                databaseRepo.updateType(
                    TypeUpdateNoOrder(
                        typeId = _typeId,
                        name = result.typeName,
                        iconId = result.iconId,
                        expenseOrIncome = result.expenseOrIncome
                    )
                )
            }
            // 成功保存
            afterSave()
        }
    }
}


fun getAddTypeKinds(context: Context): List<AddTypeKind> {
    return listOf(
        AddTypeKind(
            kindName = context.getString(R.string.entertainment),
            idList = listOf(
                R.drawable.cc_entertainmente_archery_stroke,
                R.drawable.cc_entertainmente_badminton_stroke,
                R.drawable.cc_entertainmente_baseball_stroke,
                R.drawable.cc_entertainmente_basketball_stroke,
                R.drawable.cc_entertainmente_bowling_stroke,
                R.drawable.cc_entertainmente_chess_stroke,
                R.drawable.cc_entertainmente_climbing_stroke,
                R.drawable.cc_entertainmente_gambling_stroke,
                R.drawable.cc_entertainmente_game_stroke,
                R.drawable.cc_entertainmente_movies_stroke,
                R.drawable.cc_entertainmente_ping_pong_stroke,
                R.drawable.cc_entertainmente_poker_stroke,
                R.drawable.cc_entertainmente_racing_stroke,
                R.drawable.cc_entertainmente_roller_skating_stroke,
                R.drawable.cc_entertainmente_sailing_stroke,
                R.drawable.cc_entertainmente_skiing_stroke,
                R.drawable.cc_entertainmente_swimming_stroke,
                R.drawable.cc_entertainmente_whirligig_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.catering),
            idList = listOf(
                R.drawable.cc_catering_apple_stroke,
                R.drawable.cc_catering_banana_stroke,
                R.drawable.cc_catering_beer_stroke,
                R.drawable.cc_catering_birthday_cake_stroke,
                R.drawable.cc_catering_bottle_stroke,
                R.drawable.cc_catering_cake_stroke,
                R.drawable.cc_catering_chicken_stroke,
                R.drawable.cc_catering_coffee_stroke,
                R.drawable.cc_catering_drumstick_stroke,
                R.drawable.cc_catering_hamburg_stroke,
                R.drawable.cc_catering_hot_pot_stroke,
                R.drawable.cc_catering_ice_cream_stroke,
                R.drawable.cc_catering_ice_lolly_stroke,
                R.drawable.cc_catering_noodle_stroke,
                R.drawable.cc_catering_red_wine_stroke,
                R.drawable.cc_catering_rice_stroke,
                R.drawable.cc_catering_seafood_stroke,
                R.drawable.cc_catering_skewer_stroke,
                R.drawable.cc_catering_sushi_stroke,
                R.drawable.cc_catering_tea_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.healing),
            idList = listOf(
                R.drawable.cc_medical_child_stroke,
                R.drawable.cc_medical_ct_stroke,
                R.drawable.cc_medical_doctor_stroke,
                R.drawable.cc_medical_echometer_stroke,
                R.drawable.cc_medical_injection_stroke,
                R.drawable.cc_medical_medicine_stroke,
                R.drawable.cc_medical_pregnant_stroke,
                R.drawable.cc_medical_tooth_stroke,
                R.drawable.cc_medical_transfusion_stroke,
                R.drawable.cc_medical_wheelchair_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.learn),
            idList = listOf(
                R.drawable.cc_study_blackboard_stroke,
                R.drawable.cc_study_book_stroke,
                R.drawable.cc_study_calculator_stroke,
                R.drawable.cc_study_guitars_stroke,
                R.drawable.cc_study_hat_stroke,
                R.drawable.cc_study_lamp_stroke,
                R.drawable.cc_study_penpaper_stroke,
                R.drawable.cc_study_penruler_stroke,
                R.drawable.cc_study_piano_stroke,
                R.drawable.cc_study_school_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.transportation),
            idList = listOf(
                R.drawable.cc_traffic_bike_stroke,
                R.drawable.cc_traffic_car_insurance_stroke,
                R.drawable.cc_traffic_car_stroke,
                R.drawable.cc_traffic_car_wash_stroke,
                R.drawable.cc_traffic_charge_stroke,
                R.drawable.cc_traffic_double_deck_bus_stroke,
                R.drawable.cc_traffic_expressway_stroke,
                R.drawable.cc_traffic_gasoline_stroke,
                R.drawable.cc_traffic_motorbike_stroke,
                R.drawable.cc_traffic_parking_stroke,
                R.drawable.cc_traffic_plane_stroke,
                R.drawable.cc_traffic_refuel_stroke,
                R.drawable.cc_traffic_ship_stroke,
                R.drawable.cc_traffic_taxi_stroke,
                R.drawable.cc_traffic_train_stroke,
                R.drawable.cc_traffic_truck_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.shopping),
            idList = listOf(
                R.drawable.cc_shopping_baby_stroke,
                R.drawable.cc_shopping_belt_stroke,
                R.drawable.cc_shopping_bikini_stroke,
                R.drawable.cc_shopping_boots_stroke,
                R.drawable.cc_shopping_camera_stroke,
                R.drawable.cc_shopping_cosmetics_stroke,
                R.drawable.cc_shopping_earrings_stroke,
                R.drawable.cc_shopping_eye_shadow_stroke,
                R.drawable.cc_shopping_flower_stroke,
                R.drawable.cc_shopping_flowerpot_stroke,
                R.drawable.cc_shopping_glasses_stroke,
                R.drawable.cc_shopping_hand_cream_stroke,
                R.drawable.cc_shopping_hat_stroke,
                R.drawable.cc_shopping_headset_stroke,
                R.drawable.cc_shopping_high_heels_stroke,
                R.drawable.cc_shopping_kettle_stroke,
                R.drawable.cc_shopping_knickers_stroke,
                R.drawable.cc_shopping_lipstick_stroke,
                R.drawable.cc_shopping_mascara_stroke,
                R.drawable.cc_shopping_necklace_stroke,
                R.drawable.cc_shopping_necktie_stroke,
                R.drawable.cc_shopping_package_stroke,
                R.drawable.cc_shopping_ring_stroke,
                R.drawable.cc_shopping_shopping_trolley_stroke,
                R.drawable.cc_shopping_skirt_stroke,
                R.drawable.cc_shopping_sneaker_stroke,
                R.drawable.cc_shopping_tie_stroke,
                R.drawable.cc_shopping_toiletries_stroke,
                R.drawable.cc_shopping_trousers_stroke,
                R.drawable.cc_shopping_watch_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.life),
            idList = listOf(
                R.drawable.cc_life_bath_stroke,
                R.drawable.cc_life_buddha_stroke,
                R.drawable.cc_life_candlelight_stroke,
                R.drawable.cc_life_date_stroke,
                R.drawable.cc_life_holiday_stroke,
                R.drawable.cc_life_hotel_stroke,
                R.drawable.cc_life_moods_of_love_stroke,
                R.drawable.cc_life_spa_stroke,
                R.drawable.cc_life_sunbath_stroke,
                R.drawable.cc_life_tea_stroke,
                R.drawable.cc_life_tent_stroke,
                R.drawable.cc_life_trip_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.personal),
            idList = listOf(
                R.drawable.cc_personal_clap_stroke,
                R.drawable.cc_personal_facial_stroke,
                R.drawable.cc_personal_favourite_stroke,
                R.drawable.cc_personal_friend_stroke,
                R.drawable.cc_personal_handshake_stroke,
                R.drawable.cc_personal_love_stroke,
                R.drawable.cc_personal_marry_stroke,
                R.drawable.cc_personal_money_stroke,
                R.drawable.cc_personal_pc_stroke,
                R.drawable.cc_personal_phone_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.at_home),
            idList = listOf(
                R.drawable.cc_home_air_conditioner_stroke,
                R.drawable.cc_home_bathtub_stroke,
                R.drawable.cc_home_bed_stroke,
                R.drawable.cc_home_bread_machine_stroke,
                R.drawable.cc_home_bulb_stroke,
                R.drawable.cc_home_hair_drier_stroke,
                R.drawable.cc_home_microwave_oven_stroke,
                R.drawable.cc_home_refrigerator_stroke,
                R.drawable.cc_home_renovate_stroke,
                R.drawable.cc_home_sofa_stroke,
                R.drawable.cc_home_tools_stroke,
                R.drawable.cc_home_w_and_e_stroke,
                R.drawable.cc_home_wardrobe_stroke,
                R.drawable.cc_home_washing_machine_stroke,
                R.drawable.cc_home_water_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.family),
            idList = listOf(
                R.drawable.cc_family_baby_carriage_stroke,
                R.drawable.cc_family_baby_stroke,
                R.drawable.cc_family_dog_stroke,
                R.drawable.cc_family_feeding_bottle_stroke,
                R.drawable.cc_family_nipple_stroke,
                R.drawable.cc_family_pet_food_stroke,
                R.drawable.cc_family_pet_home_stroke,
                R.drawable.cc_family_teddy_bear_stroke,
                R.drawable.cc_family_toy_duck_stroke,
                R.drawable.cc_family_wooden_horse_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.fitness),
            idList = listOf(
                R.drawable.cc_fitness_barbell_stroke,
                R.drawable.cc_fitness_bodybuilding_stroke,
                R.drawable.cc_fitness_boxing_stroke,
                R.drawable.cc_fitness_dumbbell_stroke,
                R.drawable.cc_fitness_elliptical_machine_stroke,
                R.drawable.cc_fitness_fitball_stroke,
                R.drawable.cc_fitness_hand_muscle_developer_stroke,
                R.drawable.cc_fitness_running_stroke,
                R.drawable.cc_fitness_sit_in_stroke,
                R.drawable.cc_fitness_skating_stroke,
                R.drawable.cc_fitness_treadmills_stroke,
                R.drawable.cc_fitness_weightlifting_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.office),
            idList = listOf(
                R.drawable.cc_office_clip_stroke,
                R.drawable.cc_office_computer_stroke,
                R.drawable.cc_office_desk_stroke,
                R.drawable.cc_office_drawing_board_stroke,
                R.drawable.cc_office_keyboard_stroke,
                R.drawable.cc_office_mouse_stroke,
                R.drawable.cc_office_pen_container_stroke,
                R.drawable.cc_office_pen_ruler_stroke,
                R.drawable.cc_office_printer_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.income),
            idList = listOf(
                R.drawable.cc_income_10_stroke,
                R.drawable.cc_income_1_stroke,
                R.drawable.cc_income_2_stroke,
                R.drawable.cc_income_3_stroke,
                R.drawable.cc_income_4_stroke,
                R.drawable.cc_income_5_stroke,
                R.drawable.cc_income_6_stroke,
                R.drawable.cc_income_7_stroke,
                R.drawable.cc_income_8_stroke,
                R.drawable.cc_income_9_stroke,
            )
        ),
        AddTypeKind(
            kindName = context.getString(R.string.other),
            idList = listOf(
                R.drawable.cc_other_crown_stroke,
                R.drawable.cc_other_diamond_stroke,
                R.drawable.cc_other_firecracker_stroke,
                R.drawable.cc_other_flag_stroke,
                R.drawable.cc_other_lantern_stroke,
                R.drawable.cc_other_memorial_day_stroke,
                R.drawable.cc_other_zongzi_stroke,
            )
        ),
    )
}



