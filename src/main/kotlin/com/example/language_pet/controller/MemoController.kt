package com.example.language_pet.controller

import com.example.language_pet.entity.Memo
import com.example.language_pet.repository.MemoRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/memos")
class MemoController(private val memoRepository: MemoRepository){

    @GetMapping
    fun getAllMemos(): List<Memo> = memoRepository.findAll()

    @PostMapping
    fun createMemo(@RequestBody memo: Memo): Memo = memoRepository.save(memo)

    @GetMapping("/{id}")
    fun getMemoById(@PathVariable id: Long): Memo? = memoRepository.findById(id).orElse(null)

    @PutMapping("/{id}")
    fun updateMemo(@PathVariable id: Long, @RequestBody newMemo: Memo): Memo? {
        return memoRepository.findById(id).map { existingMemo: Memo ->
            existingMemo.content = newMemo.content
            memoRepository.save(existingMemo)
        }.orElse(null)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id : Long) = memoRepository.deleteById(id)
}
