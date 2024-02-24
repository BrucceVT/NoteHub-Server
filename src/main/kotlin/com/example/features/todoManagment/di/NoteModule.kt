package com.example.features.todoManagment.di

import com.example.features.todoManagment.application.*
import com.example.features.todoManagment.domain.reposity.*
import com.example.features.todoManagment.domain.services.*
import com.example.features.todoManagment.domain.usecase.*
import com.example.features.todoManagment.infraestruture.persistence.repository.*
import com.example.features.todoManagment.infraestruture.persistence.service.*
import org.koin.dsl.*

val noteModule = module {
    single<NoteRepository> { NoteRepositoryExposed() }
    single<NoteService> {
        NoteServiceExposed(
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }
    single { NoteServiceApp(get()) }
    single {
        NoteManager(
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }

    single<GetNoteListUseCase> { GetNoteListUseCase(get()) }
    single<GetNoteDetailUseCase> { GetNoteDetailUseCase(get()) }
    single<CreateNoteUseCase> { CreateNoteUseCase(get()) }
    single<UpdateNoteUseCase> { UpdateNoteUseCase(get()) }
    single<DeleteNoteUseCase> { DeleteNoteUseCase(get()) }
}